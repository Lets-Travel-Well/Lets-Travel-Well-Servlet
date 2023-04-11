package com.ssafy.ltw.domain.myattraction.model.service;

import com.ssafy.ltw.domain.myattraction.model.MyAttraction;
import com.ssafy.ltw.domain.myattraction.model.dao.MyAttractionDao;
import com.ssafy.ltw.domain.myattraction.model.dao.MyAttractionDaoImpl;
import com.ssafy.ltw.domain.myattraction.model.dto.MyAttractionDto;

import java.util.*;

public class MyAttractionServiceImpl implements MyAttractionService{
    // TSP 관련 변수들
    private static final int MAX = Integer.MAX_VALUE;
    public static double[][] map, dp;
    public static Queue<Integer> list = new LinkedList<>();
    public static ArrayList<Double> highList = new ArrayList<>();
    public static int[] temp;
    public static double[] high;
    public static int size;
    public static double answer;

    private static MyAttractionService myAttractionService = new MyAttractionServiceImpl();
    private MyAttractionDao myAttractionDao;
    private MyAttractionServiceImpl(){
        myAttractionDao = MyAttractionDaoImpl.getInstance();
    }
    public static MyAttractionService getInstance(){
        return myAttractionService;
    }
    @Override
    public boolean changeLike(Long memberId, int attractionInfoId) throws Exception {
        boolean isLike = isExisted(memberId, attractionInfoId);
        if(!isLike){
            myAttractionDao.writeMyAttraction(memberId, attractionInfoId);
            return true;
        }
        Long findMyAttractionId = findIdByMemberAndAttractionInfo(memberId, attractionInfoId);
        myAttractionDao.removeMyAttraction(findMyAttractionId);
        return false;
    }

    @Override
    public List<MyAttractionDto> listMyAttraction(Long memberId) throws Exception {
        return myAttractionDao.findAllByMemberId(memberId);
    }


    @Override
    public boolean isExisted(Long memberId, int attractionInfoId) throws Exception {
        MyAttraction findMyAttraction = myAttractionDao.findByMemberIdAndAttractionInfoId(memberId, attractionInfoId);
        if(findMyAttraction == null){
            return false;
        }
        return true;
    }

    @Override
    public Long findIdByMemberAndAttractionInfo(Long memberId, int attractionInfoId) throws Exception {
        return myAttractionDao.findByMemberIdAndAttractionInfoId(memberId, attractionInfoId).getId();
    }

    @Override
    public MyAttractionDto findById(int attractionInfoId) throws Exception {
        return myAttractionDao.findById(attractionInfoId);
    }

    @Override
    public List<MyAttractionDto> findShortestPath(List<Integer> requestPath) throws Exception {
        List<MyAttractionDto> requestList = new ArrayList<>();
        List<MyAttractionDto> responseList = new ArrayList<>();
        for(Integer temp : requestPath){
            requestList.add(findById(temp));
        }

        size = requestList.size();
        map = new double[size][size];
        dp = new double[size][1<<size];

        for(int i = 0; i < size; i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            for(int j = i; j < size; j++){
                double len = getLength(requestList.get(i), requestList.get(j));
                map[i][j] = len;
                map[j][i] = len;
            }
        }
        temp = new int[size];
        high = new double[size];
        dp[0][1] = 0;
        temp[0] = 0;
        answer = MAX;
        double max = Integer.MIN_VALUE;
        int index = -1;
        dfs(0, 1, 1);
        for(int i = 0; i < highList.size(); i++){
            if(highList.get(i) > max){
                max = highList.get(i);
                index = i;
            }
        }
        for(int i = 0; i < index; i++){
            list.add(list.poll());
        }
        System.out.println(list);
        for(Integer temp : list){
            responseList.add(requestList.get(temp));
        }
        return responseList;
    }

    public static void dfs(int now, int visited, int cur){
        if(visited == ((1<<size)-1)){
            if(map[now][0]==0) return;
            double compare = dp[now][visited] + map[now][0];
            high[0] = map[now][0];
//            answer = Math.min(answer, compare);
            if(answer > compare){
                answer = compare;
                list = new LinkedList<>();
                highList = new ArrayList<>();
                for(Integer tmp : temp){
                    list.add(tmp);
                }
                for(double tmp : high){
                    highList.add(tmp);
                }
            }
            return;
        }

        for(int i=0; i<size; i++){
            int next = (1<<i);
            if((visited | next) == visited) continue;
            if(map[now][i] == 0) continue;
            if(dp[now][visited] + map[now][i] < dp[i][visited|next]){
                dp[i][visited|next] = dp[now][visited] + map[now][i];
                temp[cur] = i;
                high[cur] = map[now][i];
                dfs(i, visited|next, cur + 1);
                temp[cur] = -1;
            }
        }
    }
    // TODO : 유틸로 빼기
    // 위도 경도를 이용해 직선 거리 구하는 함수
    private double getLength(MyAttractionDto myAttractionDto1, MyAttractionDto myAttractionDto2){
        double lon1 = myAttractionDto1.getLongitude();
        double lon2 = myAttractionDto2.getLongitude();
        double lat1 = myAttractionDto1.getLatitude();
        double lat2 = myAttractionDto2.getLatitude();
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))* Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1))*Math.cos(deg2rad(lat2))*Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60*1.1515*1609.344;

        return dist; //단위 meter
    }
    //10진수를 radian(라디안)으로 변환
    private static double deg2rad(double deg){
        return (deg * Math.PI/180.0);
    }
    //radian(라디안)을 10진수로 변환
    private static double rad2deg(double rad){
        return (rad * 180 / Math.PI);
    }
}
