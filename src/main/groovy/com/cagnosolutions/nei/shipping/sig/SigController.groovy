package com.cagnosolutions.nei.shipping.sig

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

/**
 * Created by greg on 7/29/14.
 */

@Controller
@RequestMapping(value = "/signature")
class SigController {

    @RequestMapping(method = RequestMethod.GET)
    String view() {
        "sig/sig"
    }
}
