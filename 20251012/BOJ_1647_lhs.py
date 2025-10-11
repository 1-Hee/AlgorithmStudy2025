# BOJ_1647 : 도시 분할 계획
# https://www.acmicpc.net/problem/1647

import sys
sys.stdin = open('1647.txt', 'r')

def find_parent(parent, x):
    if parent[x] != x:
        parent[x] = find_parent(parent, parent[x])
    return parent[x]

def union_parent(parent, a, b):
    a = find_parent(parent, a)
    b = find_parent(parent, b)
    if a < b:
        parent[b] = a
    else:
        parent[a] = b

def kruskal(V, edges):
    picked = 0

    parent = [i for i in range(V + 1)]
    edges.sort(key=lambda x: x[0])

    total_cost = 0
    for cost, a, b, in edges:
        if find_parent(parent, a) != find_parent(parent, b):
            union_parent(parent, a, b)
            total_cost += cost
            last = cost
            picked += 1
            if picked == V - 1:
                break

    return total_cost - last

V, E = map(int, input().split())
edges = []

for _ in range(E):
    a, b, c = map(int, input().split())
    edges.append((c, a, b))

print(kruskal(V, edges))