package com.ssafy.goat.attraction.repository;

import com.ssafy.goat.attraction.Gugun;

import java.util.List;

public interface GugunRepository {

    List<Gugun> findBySidoCode(int sidoCode);
}
