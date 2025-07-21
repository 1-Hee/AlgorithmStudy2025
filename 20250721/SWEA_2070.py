# SWEA 2070. 큰 놈, 작은 놈, 같은 놈
# URL : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5QQ6qqA40DFAUq&categoryId=AV5QQ6qqA40DFAUq&categoryType=CODE&problemTitle=2070&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1

Test_Case_Count = int(input()) # 테스트 케이스 번호 입력받는 곳
Num_list = [input() for _ in range(Test_Case_Count)] # input 받는걸 Test_Case_Count 수 만큼 받아, 리스트로 그것을 저장한다.
Results = [] # 결과 값을 저장할 리스트 -> 여기에 결과값을 저장해서 마지막에 한번에 결과를 나타내게 함.

for Test_Case_Number in range(1, Test_Case_Count + 1): # for반복문 이용, 처음 받는 테스트 케이스 번호를 1부터 Test_case_Count에 받은 입력까지 반복하여 Test_Case_Number라는 변수에 반환
    Num1, Num2 = map(int, Num_list[Test_Case_Number - 1]. split()) # 위에서 Num_list로 받은 리스트 값에서 (Test_Case_Number - 1 -> 리스트의 0부터 사용하기 위해)0부터 Test_Case_Number - 1 까지 하나씩 꺼낸다
    if Num1 > Num2:
        result = ">"
    elif Num1 == Num2:
        result = "="
    else:
        result = "<"
    Results.append(f'#{Test_Case_Number} {result}') # 위에서 크기 비교를 한 값을 '#Test_Case_Number(숫자) result(부등호)'로 저장하고 그 값을 Results 리스트에 저장한다 

for Real_Results in Results:
    print(Real_Results) # Results리스트에 저장한 값을 Real_Results에 하나씩 꺼내서 print함



# 쒸이,,뿔,,, 어려워서 채찍질좀 했습니다.

# T = int(input())  # 테스트 케이스 개수 입력
# inputs = [input() for _ in range(T)]  # 테스트 케이스 입력 저장
# results = []  # 결과 저장용 리스트

# for t in range(1, T + 1):
#     a, b = map(int, inputs[t - 1].split())  # 각 줄의 두 수 가져오기
#     if a > b:
#         result = ">"
#     elif a < b:
#         result = "<"
#     else:
#         result = "="
#     results.append(f"#{t} {result}")

# # 결과 한꺼번에 출력
# for line in results:
#     print(line)