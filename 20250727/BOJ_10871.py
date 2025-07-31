# BOJ 10871. X보다 작은 수
# URL : https://www.acmicpc.net/problem/10871

# 첫째 줄에 N과 X를 각각 받고 둘째줄에 수열 A를 받음
N, X = map(int,input().split())
sequence_A = map(int,input().split())
# 빈 리스트 생성
min_list=[]
# for 반복문 사용 수열 A를 하나씩 꺼내 X와 크기 비교
for i in sequence_A:
    if X > i:
        min_list.append(i) # X보다 작으면 list에 append
    else:
        continue # 다른경우는 넘어감

print(*min_list) # list를 언패킹해서 print