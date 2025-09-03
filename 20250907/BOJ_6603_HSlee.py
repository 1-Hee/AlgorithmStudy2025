while True:
    data = list(map(int, input().split()))
    K = data[0]
    if K == 0:
        break
    S = data[1:]
    pick = [0] * 6

    def backtrack(k, start):
        if k == 6:
            print(*pick)
        else:
            for i in range(start, K):
                pick[k] = S[i]
                backtrack(k + 1, i + 1)

    backtrack(0, 0)
    print()