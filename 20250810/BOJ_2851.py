# BOJ 2851. 슈퍼 마리오
# https://www.acmicpc.net/problem/2851

arr = [int(input()) for _ in range(10)] # 버섯을 리스트로 입력 받음
mario_point = 0 # 초기 마리오 점수

for i in arr: # 리스트의 요소를 반복문으로 꺼냄

    if mario_point + i > 100: # 초기 점수 + 버섯점수가 100 이하일때

        # 100-점수의 절댓값이 점수+i-100의 절댓값보다 작으면 여기서 반복문 종료
        if round(100-mario_point) < round(mario_point+i-100):
            break # 점수와 더했을때의 점수중에 100에 더 가까운 값을 찾기 위함

        else:
            mario_point += i
            break # 그 외에는 버섯 점수를 더함

    else:
        mario_point += i # 위의 조건문을 제외한 경우에는 버섯 점수를 더함

print(mario_point)