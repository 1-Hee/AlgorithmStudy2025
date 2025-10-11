# BOJ_1922 : 네트워크 연결
# https://www.acmicpc.net/problem/1922

import sys
sys.stdin = open('1922.txt', 'r')

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

    parent = [i for i in range(V + 1)]
    edges.sort(key=lambda x: x[0])

    total_cost = 0
    for cost, a, b in edges:
        if find_parent(parent, a) != find_parent(parent, b):
            union_parent(parent, a, b)
            total_cost += cost


    return total_cost

V = int(input())
E = int(input())
edges = []

for _ in range(E):
    a, b, c = map(int, input().split())
    edges.append((c, a, b))

print(kruskal(V, edges))