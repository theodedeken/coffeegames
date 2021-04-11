import json

with open('../src/main/resources/resources/font.json') as file:
    schema = json.loads(file.read())
    newschema = {}
    for key, value in schema.items():
        newschema[key] = {
            'sheetName': value['sheetName'],
            'dim': {
                'x': value['width'],
                'y': value['height']
            },
            'pos': {
                'x': value['xPosRel'] * value['width'],
                'y': value['yPosRel'] * value['height']
            }
        }

with open('test', 'w') as output:
    output.write(json.dumps(newschema))
