# BOJ_1110 : 더하기 사이클
# https://www.acmicpc.net/problem/1110


N = int(input())
original = N
count = 0

def cycle(N): # 뭔가 함수 써보고싶었음.
    global original
    global count

    if N < 10:
        new_number = (N*10)+N
        count += 1
    elif (N // 10) + (N % 10) >= 10:
        new_number = (N // 10) + (N % 10)
        if new_number >= 10:
            new_number = ((N % 10) * 10) + (new_number % 10)
        count += 1
    else:
        new_number = ((N // 10) + (N % 10)) + ((N % 10) * 10)
        count += 1

    if original == new_number:
        return count

    number = new_number
    return cycle(number)

print(cycle(N))