# SWEA 1933. 간단한 N의 약수
# URL : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PhcWaAKIDFAUq&categoryId=AV5PhcWaAKIDFAUq&categoryType=CODE&problemTitle=1933&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1

Number = int(input())
# Number라는 변수에다가 input값을 int로 할당

for Measure in range(1, Number+1):
# range를 이용, 1부터 Number+1(그래야 Number까지 포함)까지 하나식 반환한다.
    if Number%Measure==0:
    # if문 이용, Number를 Measure로 나눈 몫이 0이 되는 수(약수)는 아래 print로 출력되게 됨
        print(Measure, end=" ")
        # if 조건문을 만족하는 Measure을 출력하는데 \n이 아닌 " "로 출력