import itertools
from flask import Flask, request, jsonify

app = Flask(__name__)
payments = []
id_counter = itertools.count(1)

@app.route('/payments', methods=['GET'])
def get_payments():
    return jsonify(payments)

@app.route('/payments/process', methods=['POST'])
def process_payment():
    payment = request.json
    payment['id'] = next(id_counter)
    payment['status'] = 'SUCCESS'
    payments.append(payment)
    return jsonify(payment), 201

@app.route('/payments/<int:id>', methods=['GET'])
def get_payment(id):
    for p in payments:
        if p['id'] == id:
            return jsonify(p)
    return "Not found", 404

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=8083)
