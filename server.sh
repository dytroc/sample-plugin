#!/bin/bash

# 해당 스크립트는 monun님의 server-script를 사용해서 실행합니다. (https://github.com/monun/server-script)
# 해당 서버는 공식 Paper API 말고 aroxu님의 Clip을 사용합니다. (https://github.com/aroxu/clip)

### 설정 ###

# 서버 마크 버전
version='1.18.1'

# 최대 사용 메모리
memory=8

# 서버 열 때 마다 config 리셋
reload_config=false

# debug 여부
debug=true

# 서버 닫힐 때, 강제로 다시 재시작할 여부
force_restart=false

# 사용할 서버 플러그인들
plugins=(
'https://github.com/monun/auto-reloader/releases/latest/download/AutoReloader.jar'
'https://media.forgecdn.net/files/3559/523/worldedit-bukkit-7.2.8.jar'
)

### 설정 ###


echo "Starting Paper server..."

if [ ! -d ".server" ]; then
  echo "Creating .server directory..."
  mkdir .server
fi

if [ ! -f ".server/start.sh" ]; then
  echo "Downloading server script..."
  wget -q -c -P ".server" -N "https://raw.githubusercontent.com/monun/server-script/master/.server/start.sh"
fi

cd .server || return

# config 파일 작성 (https://github.com/monun/server-script/blob/master/debug.sh#L26-L40)
if [ ! -f "start.sh.conf" ] || [ $reload_config == true ]; then
  echo "Writing config file..."
  cat << EOF > start.sh.conf
server="https://clip.aroxu.me/download?mc_version=$version"
debug=$debug
debug_port=5005
backup=false
force_restart=$force_restart
memory=$memory
plugins=(
EOF
  for plugin in "${plugins[@]}"
  do
    echo "  \"$plugin\""   >> start.sh.conf
  done
  echo ")" >> start.sh.conf
fi

if [ ! -f "server.properties" ]; then
  echo "Enabling command blocks..."
  cat << EOF > server.properties
enable-command-block=true
EOF
fi

if [ -f "start.sh" ]; then
  echo "Executing server start script..."
  chmod +x ./start.sh
  ./start.sh launch

  echo "Exiting..."
  exit
else
  echo "Server script not found!"
  echo "Exiting..."
  exit
fi