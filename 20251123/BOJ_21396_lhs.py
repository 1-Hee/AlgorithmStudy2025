import sys
sys.stdin = open('21396.txt', 'r')

T = int(input())

for _ in range(T):
    n, x = map(int, input().split())
    arr = list(map(int, input().split()))

    freq = {}
    answer = 0

    for a in arr:
        need = a ^ x
        answer += freq.get(need, 0)
        freq[a] = freq.get(a, 0) + 1

    print(answer)