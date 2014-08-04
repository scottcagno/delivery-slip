package com.cagnosolutions.nei.shipping.map
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

/**
 * Created by Scott Cagno.
 * Copyright Cagno Solutions. All rights reserved.
 */

@Controller
class MapController {

    @RequestMapping(value="/map", method=RequestMethod.GET)
    String map(@RequestParam List<String> pin) {
        def map = ["http://maps.googleapis.com/maps/api/staticmap"]
        map << "?center=40.039722,-76.304444&zoom=9&size=600x300&maptype=roadmap&scale=2"
        pin.eachWithIndex { loc, n ->
            map << "&markers=label:${n+1}|color:${loc}.pa"
        }
        def image = new BufferedOutputStream(new FileOutputStream("map.png"))
        image << new URL(map.join("")).openStream()
        image.close()
        "map/map"
    }
}
