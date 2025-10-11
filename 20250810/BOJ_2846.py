# BOJ 2846. 오르막길
# URL : https://www.acmicpc.net/problem/2846

N = int(input()) # 수열의 크기 변수

arr = list(map(int, input().split())) # 주어지는 N개의 정수(측정한 높이)
uphill = 0 # 얼마나 오르막길인지
max_uphill = 0 # 오르막길을 더한 총 값의 최대

for i in range(len(arr)-1): # 정수의 길이에서 -1만큼 반복(마지막꺼는 비교할 필요 없기때문)

    if arr[i] < arr[i+1]: # 앞의 요소가 뒤의 요소보다 작을경우
        uphill += arr[i+1]-arr[i] # 오르막길에 그 차이만큼 더해줌

        if max_uphill < uphill: # max_uphill 변수가 uphill보다 작으면
            max_uphill = uphill # 계속 더해줘서 가장 큰 오르막길을 구해줌

    elif arr[i] >= arr[i+1]: # 만일 앞의 요소가 뒤의 요소보다 크거나 같으면
        uphill = 0 # 오르막길이 아니므로 uphill을 초기화 해줌

print(max_uphill)