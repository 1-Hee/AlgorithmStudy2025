# BOJ 2920. 음계
# URL : https://www.acmicpc.net/problem/2920

scale = input() # scale 변수에 값을 입력받음

if scale == '1 2 3 4 5 6 7 8': # if 조건문으로 scale이 '1 2 3 4 5 6 7 8'을 입력받았으면
    print('ascending') # ascending을 출력
elif scale == '8 7 6 5 4 3 2 1': # elif 로 '8 7 6 5 4 3 2 1'을 입력 받으면
    print('descending') # descending을 출력
else:
    print('mixed') # 나머지의 경우에은 mixed를 출력