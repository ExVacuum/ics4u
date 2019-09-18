nums = [1, 2, 3, 4, 5, 6]
beans = ['egg', 3, 12, 'ninety-seven', False]
beanis = beans.copy()
nums.append(beans[3])
beans.extend(nums)
beans.insert(1, nums[2])
beans.remove(False)
nums.clear()
print(beans)
print(beans.index('ninety-seven'))
print(beans.count('ninety-seven'))
print(beanis)
