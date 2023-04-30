package com.ssafy.goat.hotplace.controller;

import com.ssafy.goat.common.FileStore;
import com.ssafy.goat.hotplace.UploadFile;
import com.ssafy.goat.hotplace.dto.HotPlaceDetailDto;
import com.ssafy.goat.hotplace.dto.HotPlaceDto;
import com.ssafy.goat.hotplace.dto.HotPlaceListDto;
import com.ssafy.goat.hotplace.dto.HotPlaceSearch;
import com.ssafy.goat.hotplace.service.HotPlaceService;
import com.ssafy.goat.member.dto.LoginMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

import static com.ssafy.goat.common.Message.EXPIRE_SESSION;
import static com.ssafy.goat.common.Message.REQUEST_LOGIN;

@Controller
@RequestMapping("/hotPlace")
@RequiredArgsConstructor
public class HotPlaceController {
    private final HotPlaceService hotPlaceService;

    @GetMapping("/list")
    public String doList(HttpServletRequest request, Model model) {
        String name = request.getParameter("name") == null ? "" : request.getParameter("name");
        int select = Integer.parseInt(request.getParameter("select") == null ? "1" : request.getParameter("select"));

        HotPlaceSearch hotPlaceSearch = HotPlaceSearch.builder().name(name).sortCondition(select).build();

        List<HotPlaceListDto> hotPlaces = hotPlaceService.searchHotPlaces(hotPlaceSearch);

        model.addAttribute("hotPlaces", hotPlaces);

        return "hotplaceList";
    }

    @GetMapping("/mvwrite")
    public String doMvwrite(HttpServletRequest request, @SessionAttribute(name = "userinfo") LoginMember loginMember, Model model) {
        if (loginMember == null) {
            model.addAttribute("msg", REQUEST_LOGIN);
            return "account/login";
        }
        return "addHotplace";
    }

    @PostMapping("/write")
    public String doWrite(HttpServletRequest request, @SessionAttribute(name = "userinfo") LoginMember loginMember, Model model) throws IOException, ServletException {
        if (loginMember == null) {
            request.setAttribute("msg", EXPIRE_SESSION);
            return "account/login";
        }

        String name = request.getParameter("name");
        String visitedDate = request.getParameter("visitedDate");
        int contentTypeId = Integer.parseInt(request.getParameter("contentTypeId"));
        String desc = request.getParameter("desc");
        int contentId = Integer.parseInt(request.getParameter("contentId"));

        Part part = request.getPart("hotplaceImg");

        FileStore fileStore = new FileStore();
        UploadFile uploadFile = fileStore.storeFile(part);

        HotPlaceDto hotPlaceDto = HotPlaceDto.builder().name(name).visitedDate(visitedDate).contentTypeId(contentTypeId).desc(desc).uploadFile(uploadFile).build();

        int result = hotPlaceService.addHotPlace(loginMember.getId(), contentId, hotPlaceDto);

        return "redirect:/hotplace/list";

    }

    @GetMapping("/detail")
    public String doDetail(HttpServletRequest request, @SessionAttribute(name = "userinfo") LoginMember loginMember, Model model) {
        if (loginMember == null) {
            request.setAttribute("msg", REQUEST_LOGIN);
            return "account/login";
        }

        Long hotPlaceId = Long.parseLong(request.getParameter("hotPlaceId"));

        HotPlaceDetailDto hotPlace = hotPlaceService.searchHotPlace(hotPlaceId);
        hotPlaceService.updateHit(hotPlaceId);

        model.addAttribute("hotPlace", hotPlace);
        return "viewHotplace";
    }

    @GetMapping("/mvedit")
    public String doMvedit(HttpServletRequest request, @SessionAttribute(name = "userinfo") LoginMember loginMember, Model model) {
        if (loginMember == null) {
            request.setAttribute("msg", REQUEST_LOGIN);
            return "account/login";
        }

        Long hotPlaceId = Long.parseLong(request.getParameter("hotPlaceId"));

        HotPlaceDetailDto hotPlace = hotPlaceService.searchHotPlace(hotPlaceId);

        model.addAttribute("hotPlace", hotPlace);
        return "editHotplace";
    }

    @PostMapping("/edit")
    public String doEdit(HttpServletRequest request, @SessionAttribute(name = "userinfo") LoginMember loginMember, Model model) {
        if (loginMember == null) {
            request.setAttribute("msg", EXPIRE_SESSION);
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
            request.setAttribute("msg", "로그인 후 사용해주세요.");
            return "account/login";
        }

        Long hotPlaceId = Long.parseLong(request.getParameter("hotPlaceId"));
        int result = hotPlaceService.removeHotPlace(hotPlaceId, loginMember.getId());

        return "redirect:/hotplace/list";
    }


}