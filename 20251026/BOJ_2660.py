from collections import deque

N = int(input())
lst = [[] for _ in range(N + 1)]

while True:
    a, b = map(int, input().split())
    if a == -1 and b == -1: # a, b가 둘다 -1일때까지 input, append
        break
    else:
        lst[a].append(b)    # 서로 친구임을 넣어줌
        lst[b].append(a)

scores = [0] * (N + 1)

for i in range(1, N + 1):   # 인덱스 1번(사람이 1번부터이므로)를 사용하기 위함
    friend_dist = [-1] * (N + 1)    # 친구의 친구의 친구의 친구의 친구의 친구의...
    friend_dist[i] = 0  # 본인이므로 0
    q = deque([i])      # q에 초기번호(사람)넣기~

    while q:    # q가 빌때까지 반복
        current = q.popleft()   # 현재의 q에 있는 사람을 pop
        for next in lst[current]:   # lst안에 친구들을 next에
            if friend_dist[next] == -1: # 친구의 친구의.. 리스트가 -1이면(친구거리 안쟀으면)
                friend_dist[next] = friend_dist[current] + 1    # 현재 거리에서 +1해줌
                q.append(next)  # 그걸 넣어줌

    scores[i] = max(friend_dist[1:])    # dist에서 (인덱스0제외) 가장 큰값 추출

min_score = min(scores[1:]) # 스코어값이 작은값(회장의 자1질을 가진자)를 추출
number = [i for i in range(1, N + 1) if scores[i] == min_score] # 스코어에서 min_score와 값이 값은 사람을 number에 넣기

print(min_score, len(number))   # 출@력
print(*number)  # 출@력