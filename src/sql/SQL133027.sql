/*
  주문량이 많은 아이스크림들 조회하기
  https://school.programmers.co.kr/learn/courses/30/lessons/133027
 */

SELECT F.FLAVOR
FROM FIRST_HALF F
         JOIN JULY J
              ON F.FLAVOR = J.FLAVOR
GROUP BY F.FLAVOR
ORDER BY F.TOTAL_ORDER + SUM(J.TOTAL_ORDER) DESC
    LIMIT 3;