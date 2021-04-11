import os
import json


def rename_files(root, base_name):
    for root, dirs, files in os.walk(root):

        files.sort()
        for i, filename in enumerate(files):
            os.rename(os.path.join(root, filename),
                      os.path.join(root, '{}_{}.png'.format(base_name, i)))

            print(filename)


def generate_json(root, resource_root, w, h):
    for root, dirs, files in os.walk(root):
        sprites = {}
        for file in files:
            parts = file.split('.')
            sprites[parts[0]] = {
                'path': resource_root + file,
                'dim': {
                    'x': w,
                    'y': h
                }
            }
    with open('json', 'w') as output:
        output.write(json.dumps(sprites))


rename_files("../src/main/resources/textures/animations",
             "cd_anim")
