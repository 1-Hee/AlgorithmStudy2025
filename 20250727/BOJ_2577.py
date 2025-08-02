# BOJ 2577. 숫자의 개수
# URL : https://www.acmicpc.net/problem/2577

# A,B,C의 값을 int로 받는다.
A= int(input())
B= int(input())
C= int(input())

# 변수설정
mul_number = A*B*C
number_list = []
n0 = n1 = n2 = n3 = n4 = n5 = n6 = n7 = n8 = n9 = 0

# mul_number을 문자열로 변환. 문자열의 요소 하나씩 반복해서 불러오고 그걸 number_list에 append
for i in str(mul_number):
    number_list.append(i)

# number_list의 리스트 요소를 하나씩 반복해서 불러오고 int로 정수화 하여 크기비교, n0 ~ n9까지 맞는곳에 입력
for number in number_list:
    if int(number) == 0:
        n0 += 1
    elif int(number) == 1:
        n1 += 1
    elif int(number) == 2:
        n2 += 1
    elif int(number) == 3:
        n3 += 1
    elif int(number) == 4:
        n4 += 1
    elif int(number) == 5:
        n5 += 1
    elif int(number) == 6:
        n6 += 1
    elif int(number) == 7:
        n7 += 1
    elif int(number) == 8:
        n8 += 1
    elif int(number) == 9:
        n9 += 1
    
# 값 출력
print(n0)
print(n1)
print(n2)
print(n3)
print(n4)
print(n5)
print(n6)
print(n7)
print(n8)
print(n9)