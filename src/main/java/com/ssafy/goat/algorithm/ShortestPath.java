package com.ssafy.goat.algorithm;

import attraction.AttractionInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShortestPath {

    private int n;
    private boolean[] visited;
    private double[][] map;
    private double resultMin = Double.MAX_VALUE;
    private ArrayList<Integer> arr = new ArrayList<>();
    private ArrayList<Integer> result = new ArrayList<>();

    public List<AttractionInfo> getShortestPath(List<AttractionInfo> attractionInfos) {
        n = attractionInfos.size();

        Map<Integer, AttractionInfo> store = new HashMap<>();
        for (int i = 0; i < n; i++) {
            store.put(i, attractionInfos.get(i));
        }

        map = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double distance = getDistance(attractionInfos.get(i), attractionInfos.get(j));
                map[i][j] = distance;
            }
        }

        for (int i = 0; i < n; i++) {
            visited = new boolean[n];
            visited[i] = true;
            dfs(i, i, 0);
        }

        List<AttractionInfo> res = new ArrayList<>();
        res.add(store.get(0));
        for (int index : result) {
            res.add(store.get(index));
        }
        return res;
    }

    private void dfs(int start, int now, double cost) {
        if (allVisited()) {
            if (map[now][start] != 0.0) {
                if (resultMin > cost + map[now][0]) {
                    resultMin = cost + map[now][0];
                    result = new ArrayList<>();
                    result.addAll(arr);
                }
            }
            return;
        }
        for (int i = 1; i < n; i++) {
            if (!visited[i] && map[now][i] != 0) {
                visited[i] = true;
                arr.add(i);
                dfs(start, i, cost + map[now][i]);
                arr.remove(arr.size() - 1);
                visited[i] = false;
            }
        }
    }

    public boolean allVisited() {
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }

    private double getDistance(AttractionInfo start, AttractionInfo end) {
        double x = Math.abs(start.getLatitude() - end.getLatitude());
        double y = Math.abs(start.getLongitude() - end.getLongitude());
        return Math.sqrt(x * x + y * y);
    }
}