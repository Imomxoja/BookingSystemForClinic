package uz.pdp.doctor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.doctor.domain.dto.response.BaseResponse;
import uz.pdp.doctor.domain.dto.response.history.HistoryResponse;
import uz.pdp.doctor.service.history.HistoryService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/history")
public class HistoryController {
    private final HistoryService historyService;

    @GetMapping("/get-all")
    public ModelAndView getAll(@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber) {
        ModelAndView view = new ModelAndView();
        BaseResponse<List<HistoryResponse>> allHistories = historyService.findAll(pageNumber);

        view.addObject("histories", allHistories.getData());
        view.addObject("pages", allHistories.getTotalPage());

        return view;
    }


}
