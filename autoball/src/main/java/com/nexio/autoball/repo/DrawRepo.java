package com.nexio.autoball.repo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexio.autoball.model.Draw;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DrawRepo {
    @Autowired
    private Jdbi jdbi;

    @Value("${DRAW_INSERT}")
    private String drawInsert;

    @Value("${DRAW_QUERY_BY_GAME_NUM}")
    private String queryByGameNum;

    @Value("${DRAW_QUERY_BY_GAME_STATUS}")
    private String queryByGameStatus;

    @Value("${DRAW_UPDATE_BY_GAME_NUM}")
    private String updateByGameNum;

    public void insert(Draw draw){
        jdbi.withHandle(handle -> handle.createUpdate(drawInsert).bindBean(draw).execute());
    }

    public void update(Draw draw){
//        try {
//            ObjectMapper objectMapper =  new ObjectMapper();
//
//            String json = objectMapper.writeValueAsString(draw.getGameInfo());
//            jdbi.withHandle(handle -> handle.createUpdate(updateByGameNum)
//                    .bind("gameNum",draw.getGameNum())
//                    .bind("drawStatus",draw.getDrawStatus())
//                    .bind("gameInfo",json).execute());
//        } catch (JsonProcessingException e) {
//        }

    }

    public Draw findByGameNum(String gameNum){
        Optional<Draw> optional = jdbi.withHandle(handle -> handle.createQuery(queryByGameNum).
                bind("gameNum", gameNum).mapTo(Draw.class).findFirst());
        return optional.isEmpty() ? null : optional.get();
    }

    public List<Draw> findByStatus(int gameStatus){
        List<Draw> list = jdbi.withHandle(handle -> handle.createQuery(queryByGameStatus).
                bind("gameStatus", gameStatus).mapTo(Draw.class).list());
        return list;
    }

}
