package com.cagnosolutions.nei.shipping.map
import com.cagnosolutions.nei.shipping.slip.SlipService
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.FileSystemResource
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@CompileStatic
@Controller
class MapController {

	@Autowired
	SlipService slipService

	@RequestMapping(value = "/secure/generate", method = RequestMethod.GET)
	String generate(RedirectAttributes attr) {
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
		def map = staticMap.join("")
		def image = new BufferedOutputStream(new FileOutputStream("tmp/map.png"))
		image << new URL(map).openStream()
		image.close()
		attr.addFlashAttribute("alertSuccess", "Successfully updated map")
		"redirect:/"
	}

	@RequestMapping(value = "/map", method = RequestMethod.GET)
	String getMap(Model model) {
		model.addAttribute("slips", slipService.findDeliveries())
		"map/map"
	}

	@RequestMapping(value = "/map/{filename}", method = RequestMethod.GET, produces = "application/png")
	@ResponseBody
	FileSystemResource file(@PathVariable String filename) {
		new FileSystemResource(new File("tmp/${filename}.png"))
	}
}
