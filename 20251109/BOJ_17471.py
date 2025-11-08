import sys
sys.stdin = open('17471.txt', 'r')

N = int(input())
arr = [0] + list(map(int, input().split()))
adj = [[] for _ in range(N + 1)]

for i in range(1, N + 1):
    data = list(map(int, input().split()))
    c = data[0]

    for j in range(1, c + 1):
        adj[i].append(data[j])

groupA = []
groupB = []
ans = 10**9

def is_connected(group, adj, N):
    if not group:
        return False
    visited = [False] * (N + 1)
    stack = [group[0]]
    visited[group[0]] = True
    cnt = 1

    while stack:
        cur = stack.pop()
        for nxt in adj[cur]:
            if nxt in group and not visited[nxt]:
                visited[nxt] = True
                stack.append(nxt)
                cnt += 1

    return cnt == len(group)


def dfs(idx):
    global ans
    if idx == N + 1:
        if not groupA or not groupB:
            return
        if is_connected(groupA, adj, N) and is_connected(groupB, adj, N):
            sumA = sum(arr[i] for i in groupA)
            sumB = sum(arr[i] for i in groupB)
            diff = abs(sumA - sumB)
            if diff < ans:
                ans = diff
        return

    groupA.append(idx)
    dfs(idx + 1)
    groupA.pop()

    groupB.append(idx)
    dfs(idx + 1)
    groupB.pop()

dfs(1)

if ans != 10**9:
    print(ans)
else:
    print(-1)