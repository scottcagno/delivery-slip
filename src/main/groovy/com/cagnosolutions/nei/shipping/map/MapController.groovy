package com.cagnosolutions.nei.shipping.map

import groovy.transform.CompileStatic
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@CompileStatic
@Controller
class MapController {

    @RequestMapping(value="/map", method=RequestMethod.GET)
    String map(@RequestParam List<String> pin) {
        def staticMap = ["http://maps.googleapis.com/maps/api/staticmap"]
        staticMap.add "?center=40.039722,-76.304444&zoom=9&size=600x300&maptype=roadmap&scale=2" as String
        pin.eachWithIndex { loc, n ->
            staticMap.add "&markers=label:${n+1}|color:${loc}.pa" as String
        }
        def image = new BufferedOutputStream(new FileOutputStream("map.png"))
        image << new URL(staticMap.join("")).openStream()
        image.close()
        "map/map"
    }
}
