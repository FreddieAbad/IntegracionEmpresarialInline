from flask import Flask, request, jsonify
import json

app = Flask(__name__)

shards_data = {
    "users": []
}

@app.route('/users', methods=['POST'])
def create_user():
    data = request.json
    user_data = data.get('user_data')
    shards_data["users"].append(data)
    return jsonify({"message": "User created successfully."}), 201

@app.route('/get_users', methods=['GET'])
def get_users():
    return jsonify(shards_data.get("users", []))

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)

