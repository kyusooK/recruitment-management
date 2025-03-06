<template>
    <v-card outlined>
        <v-card-title>
            SaveInterviewresult
        </v-card-title>

        <v-card-text>
            <Number label="Id" v-model="value.id" :editMode="editMode"/>
            <Number label="InterviewScore" v-model="value.interviewScore" :editMode="editMode"/>
            <Boolean label="Passed" v-model="value.passed" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="saveInterviewresult"
            >
                SaveInterviewresult
            </v-btn>
            
            <v-btn
                    color="primary"
                    text
                    @click="close"
            >
                Close
            </v-btn>
        </v-card-actions>
    </v-card>

</template>

<script>
   
    export default {
        name: 'SaveInterviewresultCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.id = 0;
            this.value.interviewScore = 0;
            this.value.passed = false;
        },
        watch: {
        },
        methods: {
            saveInterviewresult() {
                this.$emit('saveInterviewresult', this.value);
            },
            close() {
                this.$emit('closeDialog');
            },
            change() {
                this.$emit('input', this.value);
            },
        }
    }
</script>

