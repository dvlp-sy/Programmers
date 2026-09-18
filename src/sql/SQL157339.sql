/*
 특정 기간동안 대여 가능한 자동차들의 대여비용 구하기
 https://school.programmers.co.kr/learn/courses/30/lessons/157339
 */
SELECT CAR_ID,
       CAR_TYPE,
       FEE
FROM (
         SELECT C.CAR_ID AS CAR_ID,
                C.CAR_TYPE AS CAR_TYPE,
                FLOOR(C.DAILY_FEE * (1 - P.DISCOUNT_RATE / 100) * 30) AS FEE
         FROM CAR_RENTAL_COMPANY_CAR C
                  JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN P
                       ON C.CAR_TYPE = P.CAR_TYPE
         WHERE C.CAR_TYPE IN ('세단', 'SUV')
           AND P.DURATION_TYPE = '30일 이상'
           AND NOT EXISTS (
             SELECT 1
             FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY H
             WHERE H.CAR_ID = C.CAR_ID
               AND H.START_DATE <= '2022-11-30'
               AND H.END_DATE >= '2022-11-01'
         )
     ) T
WHERE FEE >= 500000
  AND FEE <= 2000000
ORDER BY FEE DESC, C.CAR_TYPE, C.CAR_ID DESC;