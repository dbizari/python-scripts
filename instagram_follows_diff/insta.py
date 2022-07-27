import csv

with open('seguidores.txt') as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=',')
    seguidores = []
    for row in csv_reader:
        if 'Foto del perfil' in row[0] or ' ' in row[0]:
            continue
        seguidores.append(row[0])

with open('seguidos.txt') as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=',')
    seguidos = []
    for row in csv_reader:
        if 'Foto del perfil' in row[0] or ' ' in row[0]:
            continue
        seguidos.append(row[0])


print("\n".join(list(set(seguidos) - set(seguidores))))
# s = open('seguidores_filtered.txt', 'w')
# s.write(str(seguidores))