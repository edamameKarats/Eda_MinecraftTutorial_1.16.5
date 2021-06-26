import PySimpleGUI as sg
import re

def readFileAndGenerateData(filePath):
  f=open(filePath,'r')
  lines=f.readlines()
  lines_strip=[line.strip() for line in lines]
  l_from=[line for line in lines_strip if '"from":' in line]
  l_to=[line for line in lines_strip if '"to":' in line]

  result_n=''
  result_e=''
  result_s=''
  result_w=''
  for item_from, item_to in zip(l_from,l_to):
    array_from=eval(re.sub(',$', '', item_from.split(':')[1]))
    array_to=eval(re.sub(',$', '', item_to.split(':')[1]))
    result_n=result_n+'Block.box('+str(array_from[0])+","+str(array_from[1])+","+str(array_from[2])+','+str(array_to[0])+","+str(array_to[1])+","+str(array_to[2])+'),'
    result_e=result_e+'Block.box('+str(16-array_from[2])+","+str(array_from[1])+","+str(array_from[0])+','+str(16-array_to[2])+","+str(array_to[1])+","+str(array_to[0])+'),'
    result_s=result_s+'Block.box('+str(16-array_from[0])+","+str(array_from[1])+","+str(16-array_from[2])+','+str(16-array_to[0])+","+str(array_to[1])+","+str(16-array_to[2])+'),'
    result_w=result_w+'Block.box('+str(array_from[2])+","+str(array_from[1])+","+str(16-array_from[0])+','+str(array_to[2])+","+str(array_to[1])+","+str(16-array_to[0])+'),'

  return '===== North =====\n'+re.sub(',$','',result_n)+'\n\n===== East =====\n'+re.sub(',$','',result_e)+'\n\n===== South =====\n'+re.sub(',$','',result_s)+'\n\n===== West =====\n'+re.sub(',$','',result_w)
  

sg.theme('DarkAmber')
layout = [  [sg.Text('Minecraft Box Generator')],
            [sg.Text('Input json file path'), sg.Input(), sg.FileBrowse('Choose file', key='inputFilePath')],
            [sg.Button('Generate box data'), sg.Button('Cansel')],
            [sg.Output(size=(80,20))]
         ]
window = sg.Window('Minecraft Box Generator v0.1', layout)
while True:
    event, values = window.read()

    if event == sg.WIN_CLOSED or event == 'Cansel':
        break
    elif event == 'Generate box data':
        data=readFileAndGenerateData(values['inputFilePath'])
        print (data)
window.close()
