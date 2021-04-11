import os
import json
import scipy.ndimage


def generate_font_file(root_dir, resource_dir):
    config = {}
    for root, dirs, files in os.walk(root_dir):
        for file in files:
            height, width, _ = scipy.ndimage.imread(
                os.path.join(root, file)).shape
            config[file.split('.')[0]] = {
                'path': resource_dir + file,
                'dim': {
                    'x': width,
                    'y': height
                }
            }
            print(width, height)
    open('json', 'w').write(json.dumps(config))


generate_font_file('../src/main/resources/textures/font', '/textures/font/')
