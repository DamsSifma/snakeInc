package org.snakeInc.api.repository;
import org.snakeInc.api.model.entity.Score;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends CrudRepository<Score, Integer> {
    @Query("SELECT s FROM Score s WHERE " +
            "(:playerId IS NULL OR s.player.id = :playerId) AND " +
            "(:snake IS NULL OR s.snake = :snake)")
    List<Score> findAllFiltered(@Param("playerId") Integer playerId, @Param("snake") String snake);
}