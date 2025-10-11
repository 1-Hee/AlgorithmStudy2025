# BOJ_15702 : 중간고사 채점
# https://www.acmicpc.net/problem/15702

N, M = map(int, input().split()) # N개의 문제, M명의 사람 수
grade_list = list(map(int, input().split())) # 문제의 배점
arr = [list(input().split()) for _ in range(M)] # 채점 결과

people = 100001 # 수험번호 고르기 위한 변수
max_grade = -1 # 최고점 구하기 위한 변수

for i in arr:
    grade = 0 # 반복문 돌 때 마다 초기화
    for j in range(1, N+1):
        if i[j] == 'O': # 문제를 맞추게 되면 grade에 그 문제에 맞는 배점을 더함
            grade += grade_list[j-1]

    if max_grade < grade: # max_grade보다 grade가 높으면
        max_grade = grade # max_grade에 grade를 넣고
        people = int(i[0]) # 그때의 수험번호를 people에 넣음

    elif max_grade == grade: # 만일 두개가 같으면
        if people > int(i[0]): # people과 같을때의 수험번호를 비교
            people = int(i[0]) # 더 작은값을 넣음

print(people, max_grade)