package com.ssafy.goat.trend.service;

import com.ssafy.goat.hotplace.HotPlace;
import com.ssafy.goat.hotplace.repository.HotPlaceJdbcRepository;
import com.ssafy.goat.hotplace.repository.HotPlaceRepository;
import com.ssafy.goat.member.Member;
import com.ssafy.goat.member.repository.MemberJdbcRepository;
import com.ssafy.goat.member.repository.MemberRepository;
import com.ssafy.goat.trend.Trend;
import com.ssafy.goat.trend.dto.TrendViewDto;
import com.ssafy.goat.trend.repository.TrendJdbcRepository;
import com.ssafy.goat.trend.repository.TrendRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public class TrendServiceImpl implements TrendService {

    private static final TrendService trendService = new TrendServiceImpl();
    private final TrendRepository trendRepository;
    private final MemberRepository memberRepository;
    private final HotPlaceRepository hotPlaceRepository;

    private TrendServiceImpl() {
        trendRepository = TrendJdbcRepository.getTrendRepository();
        memberRepository = MemberJdbcRepository.getMemberRepository();
        hotPlaceRepository = HotPlaceJdbcRepository.getHotPlaceRepository();
    }

    public static TrendService getTrendService() {
        return trendService;
    }

    @Override
    public int increaseInfo(Long memberId, Long hotPlaceId) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        if (!findMember.isPresent()) {
            throw new RuntimeException();
        }

        Optional<HotPlace> findHotPlace = hotPlaceRepository.findById(hotPlaceId);
        if (!findHotPlace.isPresent()) {
            throw new RuntimeException();
        }

        Member member = findMember.get();
        String birth = "20";
        String gender = member.getGender();
        if (isOld(gender)) {
            birth = "19";
        }
        birth += member.getBirth();
        birth = birth.substring(0, 4);

        HotPlace hotPlace = findHotPlace.get();
        Integer contentId = hotPlace.getAttractionInfo().getId();

        Optional<Trend> findTrend = trendRepository.findByContentId(contentId);
        if (!findTrend.isPresent()) {
            trendRepository.save(contentId);
            findTrend = trendRepository.findByContentId(contentId);
        }

        Trend trend = findTrend.get();
        if (isMale(gender)) {
            trend.increaseMale();
        } else {
            trend.increaseFemale();
        }

        int year = LocalDateTime.now().getYear();
        int age = year - Integer.parseInt(birth) + 1;
        switch (age / 10) {
            case 1:
                trend.increaseTeenage();
                break;
            case 2:
                trend.increaseTwenty();
                break;
            case 3:
                trend.increaseThirty();
                break;
        }

        return trendRepository.update(trend);
    }

    @Override
    public TrendViewDto popularByTeenage() {
        return trendRepository.findPopularByTeenage();
    }

    @Override
    public TrendViewDto popularByTwenty() {
        return trendRepository.findPopularByTwenty();
    }

    @Override
    public TrendViewDto popularByThirty() {
        return trendRepository.findPopularByThirty();
    }

    @Override
    public TrendViewDto popularByMale() {
        return trendRepository.findPopularByMale();
    }

    @Override
    public TrendViewDto popularByFemale() {
        return trendRepository.findPopularByFemale();
    }

    private boolean isOld(String gender) {
        return gender.equals("1") || gender.equals("2");
    }

    private boolean isMale(String gender) {
        return gender.equals("1") || gender.equals("3");
    }
}
