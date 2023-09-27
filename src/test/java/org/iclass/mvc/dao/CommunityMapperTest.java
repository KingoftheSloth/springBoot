package org.iclass.mvc.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class CommunityMapperTest {

    @Autowired
CommunityMapper dao;
    @Test
    void pagelist() {
    }

    @Test
    @DisplayName("저장된 글의 개수는 0 이 아닙니다.")
    void count() {
    int count = dao.count();
    log.info("커뮤니티 count: {}" , count);
        Assertions.assertNotEquals(0,count);
    }


    @Test
    void selectByIdx() {
    }

    @Test
    void setReadCount() {
    }

    @Test
    void insert() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}