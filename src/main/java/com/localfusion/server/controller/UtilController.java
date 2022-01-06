package com.localfusion.server.controller;

import com.localfusion.server.constant.Table;
import com.localfusion.server.constant.URL;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
public class UtilController {

    @GetMapping(value = URL.UtilController.ITEM)
    public void itemQR(final HttpServletResponse response,
                       @RequestParam(name = Table.Item.ID) final int id) throws IOException {
        response.sendRedirect(URL.UtilController.itemQR(id));
    }

}
