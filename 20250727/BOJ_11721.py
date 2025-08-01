# BOJ 11721. 열 개씩 끊어 출력하기
# URL : https://www.acmicpc.net/problem/11721

# 문자열 입력
text = input() 
# 문자열 카운트 변수 0 설정
text_count = 0

for i in text:
    if text_count < 10: # 문자열 카운트가 10보다 작으면 문자열 출력, 문자열 카운트 변수에 1 더함
        text_count += 1
        print(i, end="")
    elif text_count == 10: # 문자열 카운트가 10이 되면 문자열 카운트에 1 입력, 한줄 띄우고 출력
        text_count = 1
        print()
        print(i, end="")