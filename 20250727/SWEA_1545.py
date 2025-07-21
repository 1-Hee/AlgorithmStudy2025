# SWEA 1545. 거꾸로 출력해 보아요
# https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV2gbY0qAAQBBAS0&categoryId=AV2gbY0qAAQBBAS0&categoryType=CODE&problemTitle=%EA%B1%B0%EA%BE%B8%EB%A1%9C&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1

Number = int(input())
# Number라는 변수에다 input값을 int로 받는다

for Reverse in range(Number, -1, -1):
    print(Reverse, end=' ')
# range함수로 input받은 Number 변수를 -1만큼 줄인걸 출력, 거꾸로 하는걸 반복하여 Reverse에 저장
# Reverse를 print 하는데 함수가 출력된 후 추가할 문자를 \n이 아닌 ' ' 으로 변환해서 출력함
