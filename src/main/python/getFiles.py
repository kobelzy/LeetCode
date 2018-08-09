import json
import sys, getopt, os

opts, args = getopt.getopt(sys.argv[1:], "hi:o:")
input_dir = ""
output_file = ""
for op, value in opts:
    if op == "-i":
        input_dir = value
    elif op == "-o":
        output_file = value
    elif op == "-h":
        usage()
        sys.exit()

files = os.listdir(input_dir)


def filterOption(n):
    return n.startswith('option-') & (n != 'option-all')


def filterList(n):
    return n.startswith('list')


def filterBunble(n):
    return n.startswith('bunble')


def filterPly1(n):
    return n.startswith('option')


def filterPly2(n):
    return n.startswith('option')


# 获取option
options = list(filter(filterOption, files))
options.sort()

lists = list(filter(filterList, files))
lists.sort()

bundels = list(filter(filterBunble, files))
bundels.sort()

if len(lists) != len(options):
    usage('list文件缺失。。请检查目录:' + input_dir)
    sys.exit()
elif len(bundels) != len(options):
    usage('bundels文件缺失。。，请检查目录:' + input_dir)
    sys.exit()

groups = []
for index in range(0, len(options)):
    group = [options[index], lists[index], bundels[index]]
    groups.append(group)

map_groups = {'file': groups}

with open(output_file, 'w') as f:
    json.dump(map_groups, f)
