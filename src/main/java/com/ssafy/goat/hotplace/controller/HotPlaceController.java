package com.ssafy.goat.hotplace.controller;

import com.ssafy.goat.common.FileStore;
import com.ssafy.goat.hotplace.UploadFile;
import com.ssafy.goat.hotplace.dto.HotPlaceDetailDto;
import com.ssafy.goat.hotplace.dto.HotPlaceDto;
import com.ssafy.goat.hotplace.dto.HotPlaceListDto;
import com.ssafy.goat.hotplace.dto.HotPlaceSearch;
import com.ssafy.goat.hotplace.service.HotPlaceService;
import com.ssafy.goat.member.dto.LoginMember;
import com.ssafy.goat.trend.service.TrendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

import static com.ssafy.goat.common.Message.EXPIRE_SESSION;
import static com.ssafy.goat.common.Message.REQUEST_LOGIN;

@Controller
@RequestMapping("/hotPlace")
@RequiredArgsConstructor
@Slf4j
public class HotPlaceController {
    private final HotPlaceService hotPlaceService;
    private final TrendService trendService;

    @GetMapping("/list")
    public String doList(HttpServletRequest request, Model model) {
        String name = request.getParameter("name") == null ? "" : request.getParameter("name");
        int select = Integer.parseInt(request.getParameter("select") == null ? "1" : request.getParameter("select"));

        HotPlaceSearch hotPlaceSearch = HotPlaceSearch.builder().name(name).sortCondition(select).build();

        List<HotPlaceListDto> hotPlaces = hotPlaceService.searchHotPlaces(hotPlaceSearch);

        model.addAttribute("hotPlaces", hotPlaces);

        return "hotplace/hotplaceList";
    }

    @GetMapping("/write")
    public String write(HttpServletRequest request, @SessionAttribute(name = "userinfo") LoginMember loginMember, Model model) {
        if (loginMember == null) {
            model.addAttribute("msg", REQUEST_LOGIN);
            return "account/login";
        }
        return "hotplace/addHotplace";
    }

    @PostMapping("/write")
    public String doWrite(
            @RequestParam String name,
            @RequestParam String visitedDate,
            @RequestParam int contentTypeId,
            @RequestParam String desc,
            @RequestParam int contentId,
            @RequestParam("hotplaceImg") Part part,
            @SessionAttribute(name = "userinfo") LoginMember loginMember,
            Model model) throws IOException {
        if (loginMember == null) {
            model.addAttribute("msg", EXPIRE_SESSION);
            return "account/login";
        }

        FileStore fileStore = new FileStore();
        UploadFile uploadFile = fileStore.storeFile(part);

        HotPlaceDto hotPlaceDto = HotPlaceDto.builder().name(name).visitedDate(visitedDate).contentTypeId(contentTypeId).desc(desc).uploadFile(uploadFile).build();

        int result = hotPlaceService.addHotPlace(loginMember.getId(), contentId, hotPlaceDto);


        return "redirect:/hotPlace/list";

    }

    @GetMapping("/{hotPlaceId}")
    public String doDetail(
            @PathVariable Long hotPlaceId,
            @SessionAttribute(name = "userinfo") LoginMember loginMember,
            Model model) {
        if (loginMember == null) {
            model.addAttribute("msg", REQUEST_LOGIN);
            return "account/login";
        }

        HotPlaceDetailDto hotPlace = hotPlaceService.searchHotPlace(hotPlaceId);
        hotPlaceService.updateHit(hotPlaceId);
        trendService.increaseInfo(loginMember.getId(), hotPlaceId);

        model.addAttribute("hotPlace", hotPlace);
        return "hotplace/viewHotplace";
    }

    @GetMapping("/mvedit")
    public String doMvedit(HttpServletRequest request, @SessionAttribute(name = "userinfo") LoginMember loginMember, Model model) {
        if (loginMember == null) {
            model.addAttribute("msg", REQUEST_LOGIN);
            return "account/login";
        }

        Long hotPlaceId = Long.parseLong(request.getParameter("hotPlaceId"));

        HotPlaceDetailDto hotPlace = hotPlaceService.searchHotPlace(hotPlaceId);

        model.addAttribute("hotPlace", hotPlace);
        return "hotplace/editHotplace";
    }

    @PostMapping("/edit")
    public String doEdit(HttpServletRequest request, @SessionAttribute(name = "userinfo") LoginMember loginMember, Model model) {
        if (loginMember == null) {
            model.addAttribute("msg", EXPIRE_SESSION);
            return "account/login";
        }

        Long hotPlaceId = Long.parseLong(request.getParameter("hotPlaceId"));
        String name = request.getParameter("name");
        String desc = request.getParameter("desc");
        String visitedDate = request.getParameter("visitedDate");

        HotPlaceDto editHotPlace = HotPlaceDto.builder()
                .name(name)
                .desc(desc)
                .visitedDate(visitedDate)
                .build();

        int result = hotPlaceService.editHotPlace(loginMember.getId(), hotPlaceId, editHotPlace);

        return "redirect:/hotplace/list";
    }

    @GetMapping("/remove")
    public String doRemove(HttpServletRequest request, @SessionAttribute(name = "userinfo") LoginMember loginMember, Model model) {
        if (loginMember == null) {
            model.addAttribute("msg", "로그인 후 사용해주세요.");
            return "account/login";
        }

        Long hotPlaceId = Long.parseLong(request.getParameter("hotPlaceId"));
        int result = hotPlaceService.removeHotPlace(hotPlaceId, loginMember.getId());

        return "redirect:/hotplace/list";
    }


}