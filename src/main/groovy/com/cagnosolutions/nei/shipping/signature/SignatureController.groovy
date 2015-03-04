package com.cagnosolutions.nei.shipping.signature
import com.cagnosolutions.nei.shipping.email.EmailService
import com.cagnosolutions.nei.shipping.slip.Slip
import com.cagnosolutions.nei.shipping.slip.SlipService
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@CompileStatic
@Controller
@RequestMapping(value = "/secure/signature")
class SignatureController {


	@Autowired
	SignatureService signatureService

	@Autowired
	SlipService slipService
	
	@Autowired
	EmailService emailService

	@RequestMapping(method = RequestMethod.GET)
	String view() {
		"signature/signature"
	}

	@RequestMapping(method = RequestMethod.POST)
	String save(Signature signature) {
		signature = signatureService.save signature
		"redirect:/secure/signature/${signature.id}"
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	String selectSlip(Model model, @PathVariable Integer id) {
		model.addAllAttributes([signature: signatureService.findOne(id), slips: slipService.findAllValid()])
		"signature/signatureSlip"
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	String addSlips(@PathVariable Integer id, @RequestParam(value = "slipIds", required = false) List<Integer> slipIds, 
					RedirectAttributes attr, String signedBy) {
		if (slipIds == null || slipIds.size() < 1) {
			attr.addFlashAttribute("alertError", "No slips were selected")
			return "redirect:/secure/signature/${id}"
		}
		def signature = signatureService.findOne id
		signature.completed = new Date()
		signature.signedBy = signedBy
		signature = signatureService.save signature
		List<Slip> slips = slipService.findAll slipIds
		def emails = new HashSet<String>()
		Map map = new HashMap()
		slips.each { slip ->
			slip.signature = signature
			slip.hash = UUID.randomUUID().toString()
			slip.complete = 1
			def savedSlip = slipService.save slip
			map.put("slip", savedSlip)
			emailService.send("test@test.com", slip.email, null, "Slip Accepted", "Slip Accepted", "mail/signed.ftl", map)
			emails.add slip.email
		}
		updateMap()
		attr.addFlashAttribute("emails", emails)
		"redirect:/"
	}

    @RequestMapping(value = "/{id}/view", method = RequestMethod.GET)
    String viewOne(@PathVariable Integer id, Model model) {
        model.addAttribute("signature", signatureService.findOne(id))
		"signature/signatureView"
    }

	def updateMap() {
		def staticMap = ["http://maps.googleapis.com/maps/api/staticmap"]
		staticMap.add "?center=40.039722,-76.304444&zoom=9&size=600x300&maptype=roadmap&scale=2"
		def slips = slipService.findDeliveriesNoRepeat()
		slips.eachWithIndex { slip, n ->
			def color = "blue"
			if (slip.complete == 1) {
				color = "red"
			}
			staticMap.add "&markers=label:${n+1}|color:${color}|${slip.toString()}.pa" as String
		}
		def map = staticMap.join("").replaceAll("\\s+", "%20")
		def image = new BufferedOutputStream(new FileOutputStream("tmp/map.png"))
		image << new URL(map).openStream()
		image.close()
	}
}

