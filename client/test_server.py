from flask import Flask

app = Flask('app')

@app.route('/')
def main_path():
    return "This string of characters is more than likely at least or longer than 50 characters"

if __name__ == '__main__':
    app.run('0.0.0.0', 8080)