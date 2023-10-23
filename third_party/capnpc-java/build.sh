SCRIPT=$(realpath "$0")
DIR=$(dirname "$SCRIPT")
ARCHNAME=$(arch)

if [ -f /data/data/com.termux/files/retros_setup_complete ]; then
  PREFIX=/usr
fi

if [ ! -d capnproto-java/ ]; then
  git clone https://github.com/capnproto/capnproto-java.git $DIR/capnproto-java
fi
cd $DIR/capnproto-java

git checkout 81d18463a8f3c98f6d21d4eae27caaca6bace4f7

make
if [ "$EUID" -ne 0 ]
then
  sudo make install
else
  make install
fi
cd ..

