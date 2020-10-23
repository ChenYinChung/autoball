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

    @Value("${DRAW_PURGE}")
    private String purge;

    public void insert(Draw draw) throws JsonProcessingException {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(draw.getBalls());
            jdbi.withHandle(handle -> handle.createUpdate(drawInsert)
                    .bind("gameNum", draw.getGameNum())
                    .bind("gameId",draw.getGameId())
                    .bind("balls", json).execute());
    }

    public void update(Draw draw) throws JsonProcessingException {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(draw.getBalls());
        jdbi.withHandle(handle -> handle.createUpdate(updateByGameNum)
                    .bind("gameNum", draw.getGameNum())
//                    .bind("status",draw.getStatus())
//                    .bind("retry",draw.isRetry())
//                    .bind("retryCount",draw.getRetryCount())
                    .bind("balls", json).execute());

//
//        DRAW_UPDATE_BY_GAME_NUM = UPDATE draw SET balls = CAST(:balls AS JSONB), \
//        update_date = now() , status = :status, \
//        retry = :retry , retry_count = :retryCount \
//        WHERE game_num = :gameNum

    }

    public Draw findByGameNum(String gameNum) {
        Optional<Draw> optional = jdbi.withHandle(handle -> handle.createQuery(queryByGameNum).
                bind("gameNum", gameNum).mapTo(Draw.class).findFirst());
        return optional.isEmpty() ? null : optional.get();
    }

    public int purge(){
        int del = jdbi.withHandle(handle -> handle.execute(purge));
        return del;
    }

}
