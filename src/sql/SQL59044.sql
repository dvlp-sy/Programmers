/*
 오랜 기간 보호한 동물(1)
 https://school.programmers.co.kr/learn/courses/30/lessons/59044
 */
SELECT I.NAME AS NAME,
       I.DATETIME AS DATETIME
FROM ANIMAL_INS I
LEFT OUTER JOIN ANIMAL_OUTS O
             ON I.ANIMAL_ID = O.ANIMAL_ID
WHERE O.ANIMAL_ID IS NULL
ORDER BY I.DATETIME
    LIMIT 3
;