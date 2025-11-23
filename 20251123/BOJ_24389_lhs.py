import sys
sys.stdin = open('24389.txt', 'r')

N = int(input())

Bin = format(N, '032b') # 주어진 N을 32자리 이진수로 변환
arr = []                # 이후 이진수를 넣을 배열

for i in Bin:   # 변환한 이진수를 하나하나 비교, 반전하기위한 준비
    if i == '0':
        arr.append('1')
    else:
        arr.append('0')

inv = "".join(arr)  # 배열을 join

change_dec = int(inv, 2) + 1    # 2의 보수를 구하는것이므로 십진법으로 변환 후 +1
change_bin = format(change_dec, '032b') # 그 보수를 다시 32자리 이진수로 변환

cnt = 0
for i in range(32): # 반복문을 돌면서 2의 보수와 다른 비트수를 구함
    if Bin[i] != change_bin[i]:
        cnt += 1
print(cnt)



