# 2072. 홀수만 더하기
# https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=1&contestProbId=AV5QSEhaA5sDFAUq&categoryId=AV5QSEhaA5sDFAUq&categoryType=CODE&problemTitle=&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1&&&&&&&&&&

test_case = int(input())
case_count = 1
a = 0
str_wonhee = ""

for i in range(test_case):
    line = list(map(int, input().split()))

    for j in line:

        if j % 2 != 0:
            a += j
    str_wonhee += f'#{case_count} {a}\n'
    a = 0
    case_count +=1
    
print(str_wonhee)