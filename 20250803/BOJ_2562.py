# BOJ 2562. 최댓값
# URL : https://www.acmicpc.net/problem/2562

# num_list에 9개의 수를 정수로 받을수있게 설정, 빈 리스트, 초기값들 세팅
num_list = [int(input()) for _ in range(9)]
num_location = []
max_number = 0
number_searcher = 1

# for 반복문 이용 num_list에서 하나씩 출력,
# num_location이라는 리스트 만드는 동시에 최대값을 max_number에 입력
for i in num_list:
    num_location.append(i) 
    if i > max_number:
        max_number = i

# 최댓값이 몇번째로 오는지 확인하기위해 num_location 리스트에 있는 값을 불러와
# 반복문을 통해 위에서 찾은 max_number과 비교, 같을때까지 searcher의 값을 1씩 더해
# max값과 searcher가 같아지면 break하여 위치를 파악
for j in num_location:
    if max_number == j:
        break
    elif max_number != j:
        number_searcher += 1

# 값 출력
print(max_number)
print(number_searcher)