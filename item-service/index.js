const express = require('express');
const app = express();
app.use(express.json());

let items = ["Book", "Laptop", "Phone"];

app.get('/items', (req, res) => res.json(items));

app.post('/items', (req, res) => {
    items.push(req.body.name);
    res.status(201).send(`Item added: ${req.body.name}`);
});

app.get('/items/:id', (req, res) => {
    const id = parseInt(req.params.id);
    if (id < 0 || id >= items.length) return res.status(404).send("Not found");
    res.send(items[id]);
});

app.listen(8081, () => console.log("Item Service running on 8081"));
