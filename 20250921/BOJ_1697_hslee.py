# BOJ_1697 : 숨바꼭질
# http://acmicpc.net/problem/1697

N, K = map(int, input().split())    # N : 수빈 위치, K : 동생 위치
MAX = 100000    # 점의 최대가 10만이므로
dist = [-1] * 100001    # 거리 좌표 및 visited 대신 사용 (0도 사용하는 횟수 개념이라 -1로)
dist[N] = 0     # 처음 수빈 위치 방문처리
q = [N]         # 큐 시작지점
head = 0        # 리스트를 큐 처럼 쓰기위한 head값

def search():
    global head

    if N >= K: # 수빈이 동생보다 크거나 같은 값에 위치하면 - 만 하면 됨 (가지치기)
        print(N - K)
        return

    while head < len(q):    # 최악의 경우(10만일때까지)까지 계속 돌면서 head 를 +1해줌
        x = q[head]
        head += 1   # 큐 에서의 front 를 담당하는 부분

        if x == K:  # 만일 x 와 K가 같다면 도착했다는 뜻.
            print(dist[x])  # 그때의 dist[x]의 값을 출력
            return

        for nx in (x - 1, x + 1, x * 2):    # x에 -1, +1, *2 한 값을 nx로
            if 0 <= nx <= MAX and dist[nx] == -1:   # nx가 MAX 를 넘지않고(out of range 방지), 방문하지 않은곳이면
                dist[nx] = dist[x] + 1  # x까지 걸린 횟수에 +1 한 값을 nx에 넣음
                q.append(nx)    # q에 append

search()