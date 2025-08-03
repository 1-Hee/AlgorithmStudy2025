# BOJ 5597. 과제 안 내신 분..?
# URL : https://www.acmicpc.net/problem/5597

# 제출자 28명을 입력받을 리스트와 전체 학생 리스트를 만들기 위한 빈 리스트 변수
submit_stu = [int(input()) for _ in range(28)]
total_stu = []

# for 반복문을 이용, 전체 학생 리스트를 만들어줌
for i in range(1, 31):
    total_stu.append(i)

# 입력받은 리스트를 sort로 오름차순 정리
submit_stu.sort()

# 리스트의 차집합 이용, 전체에서 제출자 리스트를 빼고,
# set은 순서가 없기 때문에 만든 리스트를 sort로 다시 오름차순
not_submit_stu = list(set(total_stu).difference(set(submit_stu)))
not_submit_stu.sort()

# for 반복문을 이용, 미제출자 순서대로 출력
for i in not_submit_stu:
    print(i)