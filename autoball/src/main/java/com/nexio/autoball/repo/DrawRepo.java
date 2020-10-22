package com.nexio.autoball.repo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexio.autoball.model.Draw;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DrawRepo {
    @Autowired
    private Jdbi jdbi;

    @Value("${DRAW_INSERT}")
    private String drawInsert;

    @Value("${DRAW_QUERY_BY_GAME_NUM}")
    private String queryByGameNum;

    @Value("${DRAW_UPDATE_BY_GAME_NUM}")
    private String updateByGameNum;

    public void insert(Draw draw){
        jdbi.withHandle(handle -> handle.createUpdate(drawInsert).bindBean(draw).execute());
    }

    public void update(Draw draw){
        try {
            ObjectMapper objectMapper =  new ObjectMapper();

            String json = objectMapper.writeValueAsString(draw.getBalls());
            jdbi.withHandle(handle -> handle.createUpdate(updateByGameNum)
                    .bind("gameNum",draw.getGameNum())
                    .bind("balls",json).execute());
        } catch (JsonProcessingException e) {
        }

    }

    public Draw findByGameNum(String gameNum){
        Optional<Draw> optional = jdbi.withHandle(handle -> handle.createQuery(queryByGameNum).
                bind("gameNum", gameNum).mapTo(Draw.class).findFirst());
        return optional.isEmpty() ? null : optional.get();
    }

}
